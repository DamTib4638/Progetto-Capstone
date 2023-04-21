import { ProdottoDto } from "./prodotto-dto.interface";


export interface Ordinazione {
    dataPrenotazioneOrdine: any;
    dataEmissioneTransazione: any;
    idFornitore: number;
    idDipendente: number;
    idCassa: number;
    listaProdotti: ProdottoDto[];
}
