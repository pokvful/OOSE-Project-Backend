package nl.han.aim.oosevt.lamport.controllers.area.dto;

public class UpdateAreaRequestDTO extends AreaRequestDTO {
    private int id;

    public UpdateAreaRequestDTO() {
    }

    public UpdateAreaRequestDTO(int id, String name, double longitude, double latitude, int radius) {
        super(name,longitude,latitude,radius);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    protected void validateSpecificDto() {
        if(id == 0) {
            addError("id", "Id mag niet leeg zijn");
        }
    }
}
