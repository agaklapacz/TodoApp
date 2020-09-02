package io.github.mat3e;

import io.github.mat3e.model.Task;
import io.github.mat3e.model.TaskGroup;
import io.github.mat3e.model.TaskGroupRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.desktop.AppEvent;
import java.util.Set;

@Component
class Warmup implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(Warmup.class);

    private final TaskGroupRepository groupRepository;

    Warmup(final TaskGroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }
    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        logger.info("Application warmup after context refreshed");
        final String description = "ApplicationContextEvent";
        if (!groupRepository.existsByDescription(description)) {
            logger.info("No required group found! Adding it");
            var group = new TaskGroup();
            group.setDescription(description);
            group.setTasks(Set.of(
                    new Task("ContextClosedEvent", null, group),
                    new Task("ContextRefreshedEvent", null, group),
                    new Task("ContextStoppedEvent", null, group),
                    new Task("ContextStartedEvent", null, group)
            ));
            groupRepository.save(group);
        }
    }
}
