package nl.han.aim.oosevt.lamport.controllers.location.dto;

import nl.han.aim.oosevt.lamport.controllers.area.dto.AreaResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.franchise.dto.FranchiseResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.intervention.dto.response.InterventionResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.shared.dto.GeoFenceResponseDTO;
import nl.han.aim.oosevt.lamport.data.entity.Location;

import java.util.List;
import java.util.stream.Collectors;

public class LocationResponseDTO extends GeoFenceResponseDTO {
    private final int id;
    private final String name;
    private final int delay;

    private final AreaResponseDTO area;
    private final FranchiseResponseDTO franchise;
    private final List<InterventionResponseDTO> linkedInterventions;

    public LocationResponseDTO(int locationId, String name, double longitude, double latitude, int radius, AreaResponseDTO area, FranchiseResponseDTO franchise, int delay, List<InterventionResponseDTO> linkedInterventions) {
        super(longitude, latitude, radius);
        this.id = locationId;
        this.name = name;
        this.area = area;
        this.franchise = franchise;
        this.delay = delay;
        this.linkedInterventions = linkedInterventions;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AreaResponseDTO getArea() {
        return area;
    }

    public int getDelay() {
        return delay;
    }

    public FranchiseResponseDTO getFranchise() {
        return franchise;
    }

    public List<InterventionResponseDTO> getLinkedInterventions() {
        return linkedInterventions;
    }

    public static LocationResponseDTO fromData(Location location) {
        return new LocationResponseDTO(
                location.getId(),
                location.getName(),
                location.getLongitude(),
                location.getLatitude(),
                location.getRadius(),
                AreaResponseDTO.fromData(location.getArea()),
                FranchiseResponseDTO.fromData(location.getFranchise()),
                location.getDelay(),
                location
                        .getLinkedInterventions()
                        .stream()
                        .map(InterventionResponseDTO::fromData)
                        .collect(Collectors.toList())
        );
    }
}

