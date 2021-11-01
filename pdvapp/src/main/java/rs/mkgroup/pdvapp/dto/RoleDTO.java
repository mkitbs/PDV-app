package rs.mkgroup.pdvapp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.mkgroup.pdvapp.model.RoleName;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
	private Long item_id;
	private RoleName item_text;


    public RoleDTO(RoleName name) {
        this.item_text = name;
    }
}