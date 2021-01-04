package com.company;

import java.util.ArrayList;

public class Kurier extends Pracownik {
    ArrayList<Paczka> lista_paczek;
    Samochod samochod;

    Kurier(Magazyn magazyn, int id){
        super(magazyn,id);
    }

    private void PobierzPaczki(){
        this.lista_paczek = this.magazyn.PrzydzielPaczki();
    };

    private void PrzydzielPojazd(){
        if(lista_paczek != null) {
            this.samochod = this.magazyn.PrzydzielSamochod(lista_paczek);
        }
    };

    private void DostarczPaczke(){
        int id;
        System.out.println("--- DOSTARCZANIE PACZKI ---");
        System.out.println("Podaj id paczki do dostarczenia");
        id = scanner.nextInt();
        int index = ZnajdzPaczke(id);
        if(index >= 0){
            if(this.lista_paczek.get(index).numer_statusu>=3) System.err.println("Juz dostarczono te paczke!");
            else{
                this.lista_paczek.get(index).Dostarcz(id_pracownik);
                System.out.println("Dostarczono paczke");
            }
        } else {
            System.err.println("Nie znaleziono paczki o podanym ID");
        }

    }

    private int ZnajdzPaczke(int id){
        int index = -1;
        for(int i=0;i<lista_paczek.size();i++){
            if(this.lista_paczek.get(i).id == id) index = i;
        }
        return index;
    }

    private void ZglosDostarczeniePaczek(){// zwroc samochod i liste
        this.magazyn.DostarczPaczki(this.lista_paczek,this.samochod);
        this.lista_paczek.clear();
        this.samochod = null;
    }

    private void Listuj(){
        if(this.lista_paczek.size()>0){
            System.out.println("ID | ADRES | STATUS");
            for(int i=0;i<lista_paczek.size();i++){
                System.out.println(this.lista_paczek.get(i).id + " | " + this.lista_paczek.get(i).ulica_o + " | " + this.lista_paczek.get(i).status);
            }
        }
    }

    @Override
    public void pracuj(){
        while(!this.wyloguj){
            System.out.println("--- KONTO KIEROWCY ---");
            System.out.println("Co chcesz zrobic?");
            System.out.println("1. Pobierz paczki z magazynu"); // Pobiera liste paczek z magazunu;
            System.out.println("2. Przydziel pojazd"); // Pojazd zostaje przydzielony na podstawie kubatury paczek
            System.out.println("3. Dostarcz paczke"); // Dodaje do listy dostarczonych paczek i zmienia status dostarczonej paczki
            System.out.println("4. Zglos dostarczenie paczek"); // Wysyla liste dostarczonych paczek do systemu i zwraca samochod
            System.out.println("5. Wyswietl liste paczek");
            System.out.println("0. Wyjdz");

            this.opcja = scanner.nextInt();

            if(this.opcja == 1){
                PobierzPaczki();
            }else if(this.opcja == 2){
                PrzydzielPojazd();
            }else if(this.opcja == 3){
                DostarczPaczke();
            }else if(this.opcja == 4){
                ZglosDostarczeniePaczek();
            }else if(this.opcja == 5){
                Listuj();
            }
            else if(this.opcja == 0) {
                this.wyloguj = true;
            }
        }
    };

}