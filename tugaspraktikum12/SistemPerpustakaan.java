/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tugaspraktikum12;

/**
 *
 * @author Hi Luluuu
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemPerpustakaan {
    private static final String TEXT_FILE = "buku.txt";
    private static final String SERIAL_FILE = "buku.ser";
    private static List<Buku> bukuList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== MENU PERPUSTAKAAN ===");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Simpan ke buku.txt");
            System.out.println("3. Simpan objek ke buku.ser");
            System.out.println("4. Tampilkan dari buku.txt");
            System.out.println("5. Tampilkan dari buku.ser");
            System.out.println("6. Keluar");
            System.out.print("Pilihan: ");

            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1 -> tambahBuku(scanner);
                case 2 -> simpanKeFileTeks();
                case 3 -> simpanKeFileSerial();
                case 4 -> bacaDariFileTeks();
                case 5 -> bacaDariFileSerial();
                case 6 -> {
                    System.out.println("Keluar...");
                    return;
                }
                default -> System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private static void tambahBuku(Scanner scanner) {
        System.out.print("Judul buku   : ");
        String judul = scanner.nextLine();
        System.out.print("Pengarang    : ");
        String pengarang = scanner.nextLine();
        System.out.print("Tahun terbit : ");
        int tahun = scanner.nextInt();

        bukuList.add(new Buku(judul, pengarang, tahun));
        System.out.println("Buku berhasil ditambahkan!");
    }

    private static void simpanKeFileTeks() {
        try (FileWriter writer = new FileWriter(TEXT_FILE)) {
            for (Buku buku : bukuList) {
                writer.write(buku.toString() + "\n");
            }
            System.out.println("Data berhasil disimpan ke buku.txt");
        } catch (IOException e) {
            System.out.println("Kesalahan saat menyimpan ke file teks!");
        }
    }

    private static void simpanKeFileSerial() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SERIAL_FILE))) {
            oos.writeObject(bukuList);
            System.out.println("Objek Buku berhasil disimpan ke buku.ser");
        } catch (IOException e) {
            System.out.println("Kesalahan saat menyimpan objek!");
        }
    }

    private static void bacaDariFileTeks() {
        System.out.println("\n=== Data dari buku.txt ===");
        try (BufferedReader reader = new BufferedReader(new FileReader(TEXT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Kesalahan membaca buku.txt atau file belum ada.");
        }
    }

    private static void bacaDariFileSerial() {
        System.out.println("\n=== Data dari buku.ser ===");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SERIAL_FILE))) {
            List<Buku> list = (List<Buku>) ois.readObject();
            for (Buku b : list) {
                b.tampilkanInfo();
                System.out.println("-----------------");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Kesalahan membaca buku.ser atau file belum ada.");
        }
    }
}

