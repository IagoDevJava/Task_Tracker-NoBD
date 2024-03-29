package org.example.Tests;

import org.example.manager.managers_types.InMemoryHistoryManager;
import org.example.manager.managers_types.InMemoryTaskManager;
import org.example.tasks.Epic;
import org.example.tasks.Subtask;
import org.example.tasks.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryHistoryManagerTest {


    String expectedTask;
    String expectedEpic;
    String expectedSubtask;
    private InMemoryHistoryManager historyManager;
    private InMemoryTaskManager taskManager;
    private Task task;
    private Epic epic;
    private Subtask subtask;

    @BeforeEach
    void updateTaskManager() {
        historyManager = new InMemoryHistoryManager();
        taskManager = new InMemoryTaskManager();

        task = new Task("Task 1", "DescriptionTask 1",
                "2022-08-25 | 10:00", 1, 10);
        epic = new Epic("Epic 1", "DescriptionEpic 1", taskManager);
        subtask = new Subtask("Subtask 1", "DescriptionSubtask 1",
                "2022-08-25 | 10:00", 1, 10, 2);

        expectedEpic = "1,EPIC,Epic 1,IN_PROGRESS,DescriptionEpic 1,2022-08-25T10:00,PT1H10M,2022-08-25T11:10";
        expectedSubtask = "2,SUBTASK,Subtask 1.1,NEW,DescriptionSubtask 1,1,2022-08-25T10:00,PT1H20M,2022-08-25T11:20";
    }

    @Test
    void shouldAddOneTask() {
        taskManager.createTask(task);
        historyManager.add(task);

        expectedTask = historyManager.getHistory().get(0).toString();

        assertEquals(expectedTask, task.toString(), "Задачи не совпадают.");
    }

    @Test
    void shouldNotAddTask() {
        taskManager.createTask(task);

        List<Task> expectedEmptyList = List.of();

        assertEquals(expectedEmptyList, historyManager.getHistory(), "Список не пуст.");
    }

    @Test
    void shouldDoubleAddTask() {
        taskManager.createTask(task);
        historyManager.add(task);
        historyManager.add(task);

        int expectedSizeInt = 1;

        assertEquals(expectedSizeInt, historyManager.getHistory().size(), "Задача задублирована в истории просмотров");
    }

    @Test
    void shouldDeleteTask() {
        taskManager.createTask(task);
        taskManager.createTask(epic);
        taskManager.createTask(subtask);
        historyManager.add(task);
        historyManager.add(epic);
        historyManager.add(subtask);
        historyManager.remove(1);

        int expectedSizeInt = 2;

        assertEquals(expectedSizeInt, historyManager.getHistory().size(), "Задача не удалена из истории просмотров");
    }

    @Test
    void shouldDeleteEpic() {
        taskManager.createTask(task);
        taskManager.createTask(epic);
        taskManager.createTask(subtask);
        historyManager.add(task);
        historyManager.add(epic);
        historyManager.add(subtask);
        historyManager.remove(2);

        int expectedSizeInt = 2;

        assertEquals(expectedSizeInt, historyManager.getHistory().size(), "Задача не удалена из истории просмотров");
    }

    @Test
    void shouldDeleteSubtask() {
        taskManager.createTask(task);
        taskManager.createTask(epic);
        taskManager.createTask(subtask);
        historyManager.add(task);
        historyManager.add(epic);
        historyManager.add(subtask);
        historyManager.remove(3);

        int expectedSizeInt = 2;

        assertEquals(expectedSizeInt, historyManager.getHistory().size(), "Задача не удалена из истории просмотров");
    }

    @Test
    void shouldGetIdLastTask() {
        taskManager.createTask(task);
        taskManager.createTask(epic);
        taskManager.createTask(subtask);

        long expectedID = taskManager.getCreatedID();

        assertEquals(expectedID, subtask.getId(), "Id сгенерирован неверно");
    }
}