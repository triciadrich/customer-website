package com.customerwebsite.customer.website.BatchConfig;

import com.customerwebsite.customer.website.Models.Snowboard;
import com.customerwebsite.customer.website.Repositories.SnowboardRepository;
import io.micrometer.common.lang.NonNull;
import org.springframework.batch.core.Step;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

public interface Writers {
    @Component
     class BoardWriter implements ItemWriter<Snowboard> {
        @Autowired
        private SnowboardRepository snowboardRepository;

        @Value("${sleepTime}")
        private Integer SLEEP_TIME;

        @Override
        public void write(@NonNull Chunk<? extends Snowboard> snowboards) throws InterruptedException{
            snowboardRepository.saveAll(snowboards);
            Thread.sleep(SLEEP_TIME);
            System.out.println("Saved snowboards: " + snowboards);
        }


    }
}
