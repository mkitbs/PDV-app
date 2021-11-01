package rs.mkgroup.pdvapp.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class PDV2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String bukrs;

	private String last_changed_by;

	private String year;

	private String lastChangedOn;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PDV2VALUES")
	@MapKeyColumn(name = "MONTH")
	@Column(name = "VALUE")
	private Map<String, Long> listOfValues = new HashMap<String, Long>();

}
