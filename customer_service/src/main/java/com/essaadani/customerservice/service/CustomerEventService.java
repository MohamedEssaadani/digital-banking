package com.essaadani.customerservice.service;

import com.essaadani.customerservice.entities.Customer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.function.Function;

@Service
public class CustomerEventService {
   @Bean
    public Function<KStream<String, Customer>, KStream<String,Long>> kStreamFunction(){
       Format formatter = new SimpleDateFormat("dd/MM/yyyy ");

       return (input)->{
            return input
                    .map((k,v)->new KeyValue<>(formatter.format(v.getCreatedAt()),0L))
                    .groupBy((k,v)->k, Grouped.with(Serdes.String(),Serdes.Long()))
                    .count(Materialized.as("customers-count"))
                    .toStream();
        };
    }
}
