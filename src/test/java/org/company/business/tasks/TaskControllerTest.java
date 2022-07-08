package org.company.business.tasks;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.company.business.tasks.controller.TaskController;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestHTTPEndpoint(TaskController.class)
public class TaskControllerTest {


}
