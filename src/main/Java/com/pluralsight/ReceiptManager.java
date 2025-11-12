package com.pluralsight;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class ReceiptManager {

    public static void saveReceipt(Order order) {
        try {
            File dir = new File("receipts");
            if (!dir.exists()) dir.mkdirs();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
            String filename = "receipts/" + order.getOrderTime().format(formatter) + ".txt";

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                writer.write(order.toString());
            }
            System.out.println("Receipt saved: " + filename);
        } catch (IOException e) {
            System.err.println("Error saving receipt: " + e.getMessage());
        }
    }
}



