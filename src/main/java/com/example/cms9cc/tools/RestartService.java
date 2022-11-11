package com.example.cms9cc.tools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.cloud.context.restart.PauseHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestartService implements ApplicationListener<ApplicationPreparedEvent> {

    private ConfigurableApplicationContext context;

    private SpringApplication application;

    private String[] args;

    private ApplicationPreparedEvent event;

    private List<PauseHandler> pauseHandlers = Collections.emptyList();

    private long timeout;

    public void restartApp() {
        restart();
    }

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent input) {
        this.event = input;
        if (this.context == null) {
            this.context = this.event.getApplicationContext();
            this.args = this.event.getArgs();
            this.application = this.event.getSpringApplication();
            this.pauseHandlers = this.context.getBeanProvider(PauseHandler.class).orderedStream()
                    .collect(Collectors.toList());
        }
    }

    public Object restart() {
        Thread thread = new Thread(this::safeRestart);
        thread.setDaemon(false);
        thread.start();
        return Collections.singletonMap("message", "Restarting");
    }

    private Boolean safeRestart() {
        try {
            doRestart();
            System.out.println("Restarted");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public synchronized ConfigurableApplicationContext doRestart() {
        if (this.context != null) {
            this.application.setEnvironment(this.context.getEnvironment());
            close();
            // If running in a webapp then the context classloader is probably going to
            // die so we need to revert to a safe place before starting again
            ClassUtils.overrideThreadContextClassLoader(this.application.getClass().getClassLoader());
            this.context = this.application.run(this.args);
        }
        return this.context;
    }

    private void close() {
        ApplicationContext context = this.context;
        while (context instanceof Closeable) {
            try {
                ((Closeable) context).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            context = context.getParent();
        }
    }
}
