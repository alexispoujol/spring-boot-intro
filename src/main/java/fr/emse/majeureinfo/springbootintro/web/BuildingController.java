package fr.emse.majeureinfo.springbootintro.web;

import fr.emse.majeureinfo.springbootintro.dao.BuildingDao;
import fr.emse.majeureinfo.springbootintro.model.Building;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/api/building")
@Transactional
public class BuildingController {
    private final BuildingDao buildingDao;
    public BuildingController(BuildingDao buildingDao) { this.buildingDao = buildingDao; }

    @GetMapping
    public List<BuildingDto> list() {
        return buildingDao.findAll().stream().map(BuildingDto::new).collect(Collectors.toList());
    }


}




