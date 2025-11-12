package com.pluralsight;

import java.io.File;

public class ReceiptManager {

    public static void saveReceipt(Order order) {
        try {
            File dir = new File("receipts");
            if (!dir.exists()) dir.mkdirs();

