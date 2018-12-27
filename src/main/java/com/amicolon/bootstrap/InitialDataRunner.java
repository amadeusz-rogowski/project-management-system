package com.amicolon.bootstrap;

import com.amicolon.domain.Priority;
import com.amicolon.domain.State;
import com.amicolon.domain.enumerated.PriorityName;
import com.amicolon.domain.enumerated.StateName;
import com.amicolon.repositories.PriorityRepository;
import com.amicolon.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.asList;

@Component
public class InitialDataRunner implements ApplicationRunner
{
	private final StateRepository stateRepository;
	private final PriorityRepository priorityRepository;

	@Autowired
	public InitialDataRunner(StateRepository stateRepository, PriorityRepository priorityRepository)
	{
		this.stateRepository = stateRepository;
		this.priorityRepository = priorityRepository;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception
	{
		List<StateName> states = asList(StateName.values());

		states.forEach(stateName -> {
			State state = new State();
			state.setStateName(stateName);
			stateRepository.save(state);
		});


		List<PriorityName> priorities = asList(PriorityName.values());

		priorities.forEach(priorityName -> {
			Priority priority = new Priority();
			priority.setPriorityName(priorityName);
			priorityRepository.save(priority);
		});
	}

}
