package org.zalando.catwatch.backend.web.admin;

import static com.google.common.collect.Lists.newArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zalando.catwatch.backend.repo.ContributorRepository;
import org.zalando.catwatch.backend.repo.ProjectRepository;
import org.zalando.catwatch.backend.repo.StatisticsRepository;
import org.zalando.catwatch.backend.repo.populate.DatabasePopulator;

@Controller
public class ExportImportController {

	@Autowired
	private ContributorRepository contributorRepository;

	@Autowired
	private StatisticsRepository statisticsRepository;

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
    private DatabasePopulator databasePopulator;

    @RequestMapping(value = "/init", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String init() {
        databasePopulator.postConstruct();
        return "OK";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String deleteAll() {
        contributorRepository.deleteAll();
        projectRepository.deleteAll();
        statisticsRepository.deleteAll();
        return "OK";
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String importJson(@RequestBody DatabaseDto dto) {
		contributorRepository.save(dto.contributors);
		projectRepository.save(dto.projects);
		statisticsRepository.save(dto.statistics);
        return "OK";
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public DatabaseDto exportJson() {
		DatabaseDto dto = new DatabaseDto();
		dto.contributors.addAll(newArrayList(contributorRepository.findAll()));
		dto.projects.addAll(newArrayList(projectRepository.findAll()));
		dto.statistics.addAll(newArrayList(statisticsRepository.findAll()));
		return dto;
    }
}
