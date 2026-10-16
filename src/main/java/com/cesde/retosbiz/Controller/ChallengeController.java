// package com.cesde.retosbiz.Controller;

// import java.util.List;

// import org.springframework.http.HttpStatus;
// import org.springframework.web.bind.annotation.*;

// import com.cesde.retosbiz.Model.ChallengeModel;
// import com.cesde.retosbiz.Service.ChallengeService;

// @RestController
// @RequestMapping("/retos")
// public class ChallengeController {

//     private final ChallengeService challengeService;

//     public ChallengeController(ChallengeService challengeService) {
//         this.challengeService = challengeService;
//     }

//     @GetMapping
//     public List<ChallengeModel> getAll() {
//         return challengeService.getAll();
//     }

//     @GetMapping("/{id}")
//     public ChallengeModel getById(@PathVariable String id) {
//         return challengeService.getById(id);
//     }

//     @GetMapping("/estado/{estado}")
//     public List<ChallengeModel> getByEstado(@PathVariable String estado) {
//         return challengeService.getByEstado(estado);
//     }

//     @PostMapping
//     @ResponseStatus(HttpStatus.CREATED)
//     public ChallengeModel create(@RequestBody ChallengeModel reto) {
//         return challengeService.create(reto);
//     }

//     @PutMapping("/{id}")
//     public ChallengeModel update(@PathVariable String id, @RequestBody ChallengeModel reto) {
//         return challengeService.update(id, reto);
//     }

//     @DeleteMapping("/{id}")
//     @ResponseStatus(HttpStatus.NO_CONTENT)
//     public void delete(@PathVariable String id) {
//         challengeService.delete(id);
//     }
// }