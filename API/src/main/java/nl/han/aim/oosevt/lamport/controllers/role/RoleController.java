package nl.han.aim.oosevt.lamport.controllers.role;

import nl.han.aim.oosevt.lamport.controllers.role.dto.CreateRoleRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.role.dto.PermissionResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.role.dto.RoleResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.role.dto.UpdateRoleRequestDTO;
import nl.han.aim.oosevt.lamport.services.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@CrossOrigin
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("")
    public ResponseEntity<List<RoleResponseDTO>> getRoles() {
        return new ResponseEntity<>(
                roleService.getRoles(),
                HttpStatus.OK
        );
    }

    @GetMapping("permissions")
    public ResponseEntity<List<PermissionResponseDTO>> getPermissions() {
        return new ResponseEntity<>(
                roleService.getPermissions(),
                HttpStatus.OK
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<RoleResponseDTO> getRoleById(@PathVariable("id") int id) {
        return new ResponseEntity<>(
                roleService.getRoleById(id),
                HttpStatus.OK
        );
    }

    @PutMapping()
    public void updateRole(@RequestBody UpdateRoleRequestDTO updateRoleRequestDTO) {
        roleService.updateRole(updateRoleRequestDTO);
    }

    @PostMapping()
    public void createRole(@RequestBody CreateRoleRequestDTO createRoleRequestDTO) {
        roleService.createRole(createRoleRequestDTO);
    }
}
