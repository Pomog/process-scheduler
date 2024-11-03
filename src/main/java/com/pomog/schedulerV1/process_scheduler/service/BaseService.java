package com.pomog.schedulerV1.process_scheduler.service;

import response.Response;

public abstract class BaseService<T> {
    protected Response<T> generateResponse(int status, String message, T data) {
        return new Response<T> (status, message, data);
    }
}
