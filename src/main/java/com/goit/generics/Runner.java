package com.goit.generics;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Runner{

    private TaskProvider<Integer> taskProvider;
    private ExecutorFactory executorFactory;

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        Runner runner = applicationContext.getBean("runner", Runner.class);
        runner.execute();
        runner.execute();
    }

    private void execute() {
        Executor<Integer> executor = executorFactory.getIntegerExecutor();
        taskProvider.getAllTasks().forEach(executor::addTask);
        executor.execute();

        System.out.println("Valid results:");
        executor.getValidResults().forEach(System.out::println);
        System.out.println("Invalid results:");
        executor.getInvalidResults().forEach(System.out::println);
    }

    public void setTaskProvider(TaskProvider<Integer> taskProvider) {
        this.taskProvider = taskProvider;
    }

    public void setExecutorFactory(ExecutorFactory executorFactory) {
        this.executorFactory = executorFactory;
    }
}
