package rs.mkgroup.pdvapp.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.DecoderException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.mkgroup.pdvapp.dto.CompanyDTO;
import rs.mkgroup.pdvapp.dto.ExcelDTO;
import rs.mkgroup.pdvapp.dto.PDVDTO;
import rs.mkgroup.pdvapp.dto.TroskoviDTO;
import rs.mkgroup.pdvapp.exception.types.UserPrincipalNotFoundException;
import rs.mkgroup.pdvapp.model.Company;
import rs.mkgroup.pdvapp.model.Field;
import rs.mkgroup.pdvapp.model.PDV1;
import rs.mkgroup.pdvapp.model.PDV2;
import rs.mkgroup.pdvapp.model.PoreskiIzvestajPK;
import rs.mkgroup.pdvapp.model.PoreskiPodaci;
import rs.mkgroup.pdvapp.model.PoreskiIzvestajType;
import rs.mkgroup.pdvapp.model.User;
import rs.mkgroup.pdvapp.repository.CompanyRepository;
import rs.mkgroup.pdvapp.repository.FieldRepository;
import rs.mkgroup.pdvapp.repository.PDV1Repository;
import rs.mkgroup.pdvapp.repository.PDV2Repository;
import rs.mkgroup.pdvapp.repository.PoreskiPodaciRepository;
import rs.mkgroup.pdvapp.repository.UserRepository;
import rs.mkgroup.pdvapp.service.ExcelService;
import rs.mkgroup.pdvapp.service.PDVService;

@RestController
@RequestMapping("/pdv/")
public class PDVController {

	@Autowired
	private PDV1Repository pdv1repo;

	@Autowired
	private PDV2Repository pdv2repo;

	@Autowired
	private ExcelService excelService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CompanyRepository companyRepo;

	@Autowired
	private FieldRepository fieldRepo;
	
	@Autowired
	private PDVService pdvService;

	@GetMapping("getAll/{bukrs}/{year}")
	public ResponseEntity<List<PDVDTO>> getAllByBukrs(@PathVariable String bukrs, @PathVariable String year) {
		List<PDV1> retVal = pdv1repo.findByBukrsAndYear(bukrs, year);
		List<PDV2> retVal2 = pdv2repo.findByBukrsAndYear(bukrs, year);
		List<PDVDTO> list2return = new ArrayList<PDVDTO>();

		retVal.forEach(rv1 -> {
			User user = userRepository.findByEmail(rv1.getLast_changed_by()).get();
			rv1.setLast_changed_by(user.getName() + " " + user.getSurname());
			list2return.add(new PDVDTO(rv1));
		});

		retVal2.forEach(rv2 -> {
			User user = userRepository.findByEmail(rv2.getLast_changed_by()).get();
			rv2.setLast_changed_by(user.getName() + " " + user.getSurname());
			list2return.add(new PDVDTO(rv2));
		});

		return new ResponseEntity<List<PDVDTO>>(list2return, HttpStatus.OK);
	}

	@PostMapping("new")
	public ResponseEntity<?> postNew(@RequestBody List<PDVDTO> pdvdtolist) {
		PDVDTO pdv1dto = pdvdtolist.get(0);
		PDVDTO pdv2dto = pdvdtolist.get(1);

		PDV1 pdv1 = pdv1repo.getOne(pdv1dto.getId());
		PDV2 pdv2 = pdv2repo.getOne(pdv2dto.getId());

		if (pdv1dto.getId() == 0) { // prvi put se unosi
		
			PDV1 entity = new PDV1();
			entity.setBukrs(pdv1dto.getBukrs());
			entity.setLast_changed_by(pdv1dto.getLastChangedBy());
			entity.setLastChangedOn(new Date().toLocaleString());
			entity.setListOfValues(pdv1dto.getListOfValues1());
			entity.setName(pdv1dto.getName());
			entity.setYear(pdv1dto.getYear());
			pdv1repo.save(entity);
		} else {
			pdv1.setListOfValues(pdv1dto.getListOfValues1());
			pdv1.setLastChangedOn(new Date().toLocaleString());
			pdv1.setLast_changed_by(pdv1dto.getLastChangedBy());
			pdv1repo.save(pdv1);
		}

		if (pdv2dto.getId() == 0) { // prvi put se unosi

			PDV2 entity = new PDV2();
			entity.setBukrs(pdv2dto.getBukrs());
			entity.setLast_changed_by(pdv2dto.getLastChangedBy());
			entity.setLastChangedOn(new Date().toLocaleString());
			entity.setListOfValues(pdv2dto.getListOfValues2());
			entity.setName(pdv2dto.getName());
			entity.setYear(pdv2dto.getYear());
			pdv2repo.save(entity);
		} else {
			pdv2.setListOfValues(pdv2dto.getListOfValues2());
			pdv2.setLast_changed_by(pdv2dto.getLastChangedBy());
			pdv2.setLastChangedOn(new Date().toLocaleString());
			pdv2repo.save(pdv2);
		}

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("generateExcel/{year}")
	public ResponseEntity<ByteArrayResource> generateExcel(HttpServletResponse response, @PathVariable String year)
			throws IOException, EncryptedDocumentException, InvalidFormatException, DecoderException {

		List<ExcelDTO> excel2send = new ArrayList<ExcelDTO>();
		List<Company> companies = companyRepo.findAll();
		for (Company c : companies) {
			ExcelDTO ex = new ExcelDTO();
			ex.setBukrs(c.getCompanyCode());
			ex.setCompanyName(c.getCompanyName());
			List<PDV1> pdv1e = pdv1repo.findByBukrsAndYear(c.getCompanyCode(), year);
			List<PDV2> pdv2e = pdv2repo.findByBukrsAndYear(c.getCompanyCode(), year);
			ex.setPdv1(new HashMap<String, Long>());
			ex.setPdv2(new HashMap<String, Long>());
			Map<String, Long> sumUp = new HashMap<String, Long>();

			if (pdv1e.size() > 0) {
				ex.setPdv1(pdv1e.get(0).getListOfValues());
			}
			if (pdv2e.size() > 0) {
				ex.setPdv2(pdv2e.get(0).getListOfValues());
			}

			Iterator it = ex.getPdv1().entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();
				Iterator it2 = ex.getPdv2().entrySet().iterator();
				while (it2.hasNext()) {
					Map.Entry pair2 = (Map.Entry) it2.next();
					System.out.println(pair.getKey().toString() + " ISTO " + (pair2.getKey().toString()));
					if (pair.getKey().toString().equals(pair2.getKey().toString())) {
						System.out.println(
								"sumiram " + pair.getValue().toString() + "  +  " + pair2.getValue().toString());
						sumUp.put(pair.getKey().toString(), Long.valueOf(new Long((long) pair.getValue()).intValue()
								- new Long((long) pair2.getValue()).intValue()));
					}
				}
			}

			ex.setListSumValues(sumUp);

			excel2send.add(ex);
		}

		excelService.fillTemplatePDV(excel2send, year);

		try {
			File currDir = new File(".");
			String path = currDir.getAbsolutePath();
			String fileLocation = path.substring(0, path.length() - 1) + "Izvestaj.xls";
			File file = new File(fileLocation);

			Path patha = Paths.get(file.getAbsolutePath());
			ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(patha));

			return ResponseEntity.ok().headers(new HttpHeaders()).contentLength(file.length())
					.contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
		} catch (IOException ex) {
			System.out.println("Error writing file to output stream. Filename was " + "Izvestaj.xls");
			throw new RuntimeException("IOError writing file to output stream");
		}
	}

	@GetMapping("companies")
	public ResponseEntity<List<CompanyDTO>> getCompanies(HttpServletRequest request) {

		List<CompanyDTO> dtos = new ArrayList<CompanyDTO>();
		User logged = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
				.get();
		System.out.println("companies logged " + logged.getBukrs2access());
		String[] tokens = logged.getBukrs2access().split(";");
		System.out.println(tokens.length);
		companyRepo.findAll().forEach(co -> {
			for (String token : tokens) {
				System.out.println(token + " token");
				if (token.equals(co.getCompanyCode())) {
					dtos.add(new CompanyDTO(co));
				}
			}
			/*
			 * if(tokens.length == 1) { System.out.println(co.getCompanyCode() + " ? " +
			 * logged.getBukrs2access());
			 * if(co.getCompanyCode().equals(logged.getBukrs2access())) { dtos.add(new
			 * CompanyDTO(co)); } }
			 */
		});
		if(logged.getEmail().equals("abijelic") || logged.getEmail().equals("olivera")) {
			ArrayList<CompanyDTO> dtos2 = new ArrayList<CompanyDTO>();
			companyRepo.findAll().forEach(c -> {
				dtos2.add(new CompanyDTO(c));
			});
			return new ResponseEntity<List<CompanyDTO>>(dtos2, HttpStatus.OK);
		}
		
		return new ResponseEntity<List<CompanyDTO>>(dtos, HttpStatus.OK);
	
	}
	

	@GetMapping("generateExcelTroskovi/{year}")
	public ResponseEntity<ByteArrayResource> generateExcelTroskovi(HttpServletResponse response,
			@PathVariable String year) throws IOException, EncryptedDocumentException, InvalidFormatException {

		List<TroskoviDTO> troskovidto = new ArrayList<TroskoviDTO>();
		List<Company> companies = companyRepo.findAll();
		// System.out.println(companies.size() + " size kompanija");
		for (Company c : companies) {
			TroskoviDTO to = new TroskoviDTO();
			List<Field> fieldsOfCompany = fieldRepo.findByYearAndBukrs(year, c.getCompanyCode());
			to.setCompanyName(c.getCompanyName());
			to.setListaFieldova(fieldsOfCompany);
			troskovidto.add(to);
		}
		excelService.fillTemplateTroskovi(troskovidto, year);

		try {
			File currDir = new File(".");
			String path = currDir.getAbsolutePath();
			String fileLocation = path.substring(0, path.length() - 1) + "IzvestajDrugo.xls";
			File file = new File(fileLocation);

			Path patha = Paths.get(file.getAbsolutePath());
			ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(patha));

			return ResponseEntity.ok().headers(new HttpHeaders()).contentLength(file.length())
					.contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
		} catch (IOException ex) {
			System.out.println("Error writing file to output stream. Filename was " + "IzvestajDrugo.xls");
			throw new RuntimeException("IOError writing file to output stream");
		}

	}
	
	@PostMapping("new/drugo")
	public ResponseEntity<?> postNewDrugo(@RequestBody List<Field> pdvdtolist) {
		for(int i = 0; i < pdvdtolist.size(); i++ ) {
			Field field = fieldRepo.findByInidAndBukrsAndYear(pdvdtolist.get(i).getInid(), pdvdtolist.get(i).getBukrs(), pdvdtolist.get(i).getYear());
			if(field == null ) { //novi
				System.out.println("if");
				Field f = new Field();
				f.setBukrs(pdvdtolist.get(i).getBukrs());
				f.setInid(pdvdtolist.get(i).getInid());
				User user = userRepository.findByEmail(pdvdtolist.get(i).getLastChangedBy()).get();
				f.setLastChangedBy(user.getName() + " " + user.getSurname());
				f.setLastChangedOn(new Date().toLocaleString());
				f.setValue(pdvdtolist.get(i).getValue());
				f.setYear(pdvdtolist.get(i).getYear());
				fieldRepo.save(f);
			} else {
				System.out.println("else");
				User user = userRepository.findByEmail(pdvdtolist.get(i).getLastChangedBy()).get();
				field.setLastChangedBy(user.getName() + " " + user.getSurname());
				field.setLastChangedOn(new Date().toLocaleString());
				field.setValue(pdvdtolist.get(i).getValue());
				fieldRepo.save(field);
			}

		}

		return new ResponseEntity<>(HttpStatus.CREATED);

	}
		
	
	@GetMapping("fields/{bukrs}/{year}")
	public ResponseEntity<List<Field>> getAllFields(@PathVariable String bukrs, @PathVariable String year) {
		return new ResponseEntity<List<Field>>(fieldRepo.findByYearAndBukrs(year, bukrs), HttpStatus.OK);
	}
	
	@PostMapping("new/poreskiPodaci")
	public ResponseEntity<?> postPoreskiPodaci(Principal principal, @RequestBody List<PoreskiPodaci> poreskiToAdd) {
		
		User operationUser = userRepository.findCurrentUser()
										   .orElseThrow(() -> new UserPrincipalNotFoundException("Error loading principal user."));
		
		pdvService.createNewPoreskiPodaci(operationUser, poreskiToAdd);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@GetMapping("poreskiPodaci/{type}/{bukrs}")
	public ResponseEntity<?> getPoreskiPodaci(@PathVariable PoreskiIzvestajType type, @PathVariable String bukrs) {
		
		List<PoreskiPodaci> poreskiPodaci = pdvService.getPoreskiPodaci(type, bukrs);
		
		return new ResponseEntity<List<PoreskiPodaci>>(poreskiPodaci, HttpStatus.OK);
		
	}	
	
	@GetMapping("generatePoreskiGubici/{type}/{year}") 
	public ResponseEntity<?> generatePoreskiGubici(@PathVariable PoreskiIzvestajType type, @PathVariable String year) throws EncryptedDocumentException, InvalidFormatException, IOException {
		
		excelService.fillPoreskiGubiciExcel(type, year);
		
		try {
			File currDir = new File(".");
			String path = currDir.getAbsolutePath();
			String fileLocation = path.substring(0, path.length() - 1) + "IzvestajPoreskiGubici.xls";
			File file = new File(fileLocation);

			Path patha = Paths.get(file.getAbsolutePath());
			ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(patha));

			return ResponseEntity.ok().headers(new HttpHeaders()).contentLength(file.length())
					.contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
		} catch (IOException ex) {
			System.out.println("Error writing file to output stream. Filename was " + "IzvestajPoreskiGubici.xls");
			throw new RuntimeException("IOError writing file to output stream");
		}
	}
}
