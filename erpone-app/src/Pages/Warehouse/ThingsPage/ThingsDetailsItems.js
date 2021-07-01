import AddShoppingCartIcon from '@material-ui/icons/AddShoppingCart';
import DescriptionIcon from '@material-ui/icons/Description';
import DomainIcon from '@material-ui/icons/Domain';
import MonetizationOnIcon from '@material-ui/icons/MonetizationOn';
import TimelineIcon from '@material-ui/icons/Timeline';
import PriceCard from './Cards/PriceCard';
import DetailsCard from './Cards/DetailsCard';
import StocksCard from './Cards/StocksCard';
import LastDocumentsCard from './Cards/LastDocumentsCard';

const ThingsDetailsItems = [
  
  {
    id: "1",
    nameHeader: "Zużycie",
    avatar: DomainIcon,
    route: "stocks",
    component: StocksCard,
    subTitle: "Zużycie(na ile dni starczy stanu, zużycie w przeciągu ostatnich 3 miesięcy, zużycie w analogicznym okresie roku poprzedniego, najbliższe dostawy"
  },
  {
    id: "2",
    nameHeader: "Dokumenty",
    avatar: TimelineIcon,
    route: "sale",
    component: LastDocumentsCard,
    subTitle: "Ostatnie dokumenty, czyli po prostu lista ostatnich dokumentów, w których znajduje się dany surowiec."
  },
          {
    id: "3",
    nameHeader: "Cena",
    avatar: MonetizationOnIcon,
    route: "prices",
    component: PriceCard,
    subTitle: "Wykres pokazujący ceny z ostatnich 6 miesięcy. Wchodząc w szczegóły tej karty możesz zobaczyć historię cen danego towaru/artykułu. Wykresy z wybranego przez siebie okresu i wiele więcej."
  },
            {
    id: "4",
    nameHeader: "Szczegóły",
    avatar: DescriptionIcon,
    route: "details",
    component: DetailsCard,
    subTitle: "Znajdziesz tu wszystkie wymiary, wagi, zdjęcia, karty surowca i uwagi. Dla ułatwienia podgląd do najważniejszych atrybutów jest poniżej."
  },
];

export default ThingsDetailsItems;