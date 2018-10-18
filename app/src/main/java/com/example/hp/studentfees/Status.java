package com.example.hp.studentfees;

public class Status {
    String Id;
    String Status;

    public Status(String id, String status) {
        Id = id;
        Status = status;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
