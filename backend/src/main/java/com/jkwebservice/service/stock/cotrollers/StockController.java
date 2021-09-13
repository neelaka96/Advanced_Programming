package com.jkwebservice.service.stock.cotrollers;

import com.jkwebservice.service.models.Stock;
import com.jkwebservice.service.stock.repos.StockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StockController {

    @Autowired
    private StockRepo stockRepo;
    private Integer finalResult;

    @GetMapping("/stock/{id}")
    public Stock getStock(@PathVariable Long id) {

        Stock stock= new Stock();

        Optional<Stock> details = stockRepo.findById(id);
        if (details.isPresent()) {
            stock = details.get();
        }

        return stock;
    }

    @GetMapping("/stock")
    public List<Stock> getStockes() {

        return stockRepo.findAll();
    }

    @PostMapping("/stock")
    public Stock addStock(@RequestBody Stock stock){

        return stockRepo.save(stock);

    }

    @PutMapping("/stock/{id}")
    public Stock updateStock(@PathVariable Long id, @RequestBody Stock stockData){


        Stock updatedStock = new Stock();

        Optional<Stock> details = stockRepo.findById(id);
        if (details.isPresent()){
            Stock stock = new Stock();
            stock = details.get();

            stock.setId(stockData.getId());
            stock.setFromBranchId(stockData.getFromBranchId());
            stock.setToBranchId(stockData.getToBranchId());
            stock.setProductId(stockData.getProductId());
            stock.setStock_qty(stockData.getStock_qty());
            stock.setDate(stockData.getDate());

            updatedStock = stockRepo.save(stock);

        }
        return updatedStock;
    }

    @DeleteMapping("/stock/{id}")
    public ResponseEntity<?> deleteStock(@PathVariable Long id){
        Optional<Stock> stock = stockRepo.findById(id);
        if (stock.isPresent()){
            Stock stock1 = new Stock();
            stock1 = stock.get();

            stockRepo.delete(stock1);
        }

        return ResponseEntity.ok().build();
    }
}
