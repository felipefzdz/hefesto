package org.olid16.infrastructure.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.actions.CreateEmployer;
import org.olid16.infrastructure.rest.controllers.EmployerController;
import spark.Request;

import static builders.UserBuilder.aUser;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EmployerControllerShould {

    @Mock CreateEmployer createEmployer;
    @Mock Request request;

    @Test public void
    call_create_employer(){
        given(request.body()).willReturn("{}");
        given(createEmployer.with(any(JsonEntity.class))).willReturn(aUser().build());
        new EmployerController(createEmployer).create(request, null);
        verify(createEmployer).with(any(JsonEntity.class));
    }

    @Test public void
    return_employer_id(){
        given(request.body()).willReturn("{}");
        given(createEmployer.with(any(JsonEntity.class))).willReturn(aUser().build());
        String id = new EmployerController(createEmployer).create(request, null);
        assertThat(id).is("1234");
        
    }
}
