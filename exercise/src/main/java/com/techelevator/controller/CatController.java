package com.techelevator.controller;

import com.techelevator.dao.CatCardDao;
import com.techelevator.model.CatCard;
import com.techelevator.model.CatFact;
import com.techelevator.model.CatPic;
import com.techelevator.services.CatFactService;
import com.techelevator.services.CatPicService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CatController {

    private CatCardDao cat;
    private CatFactService catFact;
    private CatPicService catPic;

    public CatController(CatCardDao cat, CatFactService catFact, CatPicService catPic) {
        this.cat = cat;
        this.catFact = catFact;
        this.catPic = catPic;
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public CatCard makeNewCard(){
        CatFact f = catFact.getFact();
        CatPic p = catPic.getPic();
        CatCard c = new CatCard();
        c.setCatFact(f.getText());
        c.setImgUrl(p.getFile());

        return c;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CatCard> catCardList(){
        return cat.list();
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public CatCard catCardGet(@PathVariable int id){
        return cat.get(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public CatCard create(@RequestBody CatCard catCard){
        cat.save(catCard);
        return catCard;
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public CatCard update(@RequestBody CatCard catCard, @PathVariable int id){
        boolean updatedCard = cat.update(id, catCard);
        if (updatedCard == true){
            return catCard;
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Auction not found");
        }
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id){
        cat.delete(id);
    }
}
