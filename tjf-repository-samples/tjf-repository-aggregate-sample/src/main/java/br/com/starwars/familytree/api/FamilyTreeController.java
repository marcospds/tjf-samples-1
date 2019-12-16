package br.com.starwars.familytree.api;

import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.starwars.familytree.model.FamilyTree;
import br.com.starwars.familytree.model.Person;
import br.com.starwars.familytree.model.Relative;
import br.com.starwars.familytree.repository.FamilyTreeRepository;
import br.com.starwars.familytree.repository.PersonRepository;

@RestController
@RequestMapping(path = "api/v1/familytree", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class FamilyTreeController {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private FamilyTreeRepository treeRepository;

	@PostMapping("person/{personId}/relative/{relativeId}/{relationship}")
	public void addPerson(@PathVariable String personId, @PathVariable String relativeId,
			@PathVariable String relationship) {

		FamilyTree fm = treeRepository
				.findOne("data @> ?", new SqlParameterValue(Types.OTHER, "{\"id\":\"" + personId + "\"}"))
				.orElse(null);

		if (fm == null) {
			Person person = personRepository.get(personId).orElseThrow();
			fm = new FamilyTree(person);
			treeRepository.insert(fm);
		}

		Person relativePerson = personRepository.get(relativeId).orElseThrow();
		Relative relative = new Relative(relativePerson.getId(), relativePerson.getName(), relativePerson.getGender(),
				relationship);
		fm.addRelative(relative);

		treeRepository.update(fm);
	}

	@GetMapping("person/{personId}")
	public FamilyTree getPersonFamilyTree(@PathVariable String personId) {
		return treeRepository
				.findOne("data->'person' @> ?", new SqlParameterValue(Types.OTHER, "{\"id\":\"" + personId + "\"}"))
				.orElse(null);
	}

}
