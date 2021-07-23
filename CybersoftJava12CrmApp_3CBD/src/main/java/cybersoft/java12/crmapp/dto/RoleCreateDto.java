package cybersoft.java12.crmapp.dto;

import cybersoft.java12.crmapp.model.Role;

public class RoleCreateDto {
    /* Data Transfer Object */
    private String name;
    private String description;
    
    public RoleCreateDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}