package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.saveUser("Yaroslav", "Latushkov", (byte) 20);
        userService.cleanUsersTable();
    }
}
