import { Scaffale } from "./scaffale.interface";

export interface Prodotto {
    idProdotto: any;
    nome: string;
    marca: string;
    descrizione: any;
    ingredienti: string;
    tipoProdotto: string;
    categoriaProdotto: string;
    dataScadenza: any;
    qtaDisponibile: any;
    pesoDisponibile: any;
    prezzoAcquistoUnitario: number;
    prezzoVenditaUnitario: number;
    percentualeOfferta: any;
    scaffale: any;
}
