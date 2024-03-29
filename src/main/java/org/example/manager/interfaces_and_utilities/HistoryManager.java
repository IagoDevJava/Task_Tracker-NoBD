package org.example.manager.interfaces_and_utilities;

import org.example.tasks.Task;

import java.util.List;

public interface HistoryManager {

    /**
     * помечает задачи как просмотренные.
     */
    void add(Task task);

    /**
     * удаляет задачи из списка просмотренных.
     */
    void remove(long id);

    /**
     * возвращает список просмотренных задач.
     */
    List<Task> getHistory();
}