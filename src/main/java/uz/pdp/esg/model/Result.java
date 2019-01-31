package uz.pdp.esg.model;

public class Result {
    private String id;
    private String message;
    private boolean success;

    public Result() {
    }

    public Result(String id, String message, boolean success) {
        this.id = id;
        this.message = message;
        this.success = success;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
