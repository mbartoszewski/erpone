import AddShoppingCartIcon from '@material-ui/icons/AddShoppingCart';
import DescriptionIcon from '@material-ui/icons/Description';
import DomainIcon from '@material-ui/icons/Domain';
import MonetizationOnIcon from '@material-ui/icons/MonetizationOn';
import TimelineIcon from '@material-ui/icons/Timeline';
import PriceCard from './Cards/PriceCard';
import DetailsCard from './Cards/DetailsCard';
import StocksCard from './Cards/StocksCard';
import PurchaseCard from './Cards/PurchaseCard';
import SalesCard from './Cards/SalesCard';

const ThingsDetailsItems = [
  
  {
    id: "1",
    nameHeader: "Stany",
    avatar: DomainIcon,
    route: "stocks",
    component: StocksCard,
    subTitle: "Karta ta pokazuje stan oraz stan handlowy (uwzględniający rezerwację danej rzeczy) oraz przybliżony czas na jaki starczy surowca na podstawie zużycia. W szczegółach możesz zobaczyć wszystkie rezerwacje, rozchody, przychody."
  },
    {
    id: "2",
    nameHeader: "Zakup",
    avatar: AddShoppingCartIcon,
    route: "purchase",
    component: PurchaseCard,
    subTitle: "Możesz tutaj zobaczyć ostatnie dostawy. Po wejściu w kartę zobaczysz wszystkie dostawy + zamówienia jakie były zrobione na dany towar. Dodatkowo analiza, która pokaże czy towar wjeżdza zgodnie z datą realizacji z zamówienia."
  },
  {
    id: "3",
    nameHeader: "Sprzedaż",
    avatar: TimelineIcon,
    route: "sale",
    component: SalesCard,
    subTitle: "Karta ta przedstawia średnie zużycie surowca/artykułu na podstawie dokumentów magazynowych/sprzedażowych w okresie 3,6,9,12 miesięcy. Po przejściu w szczegóły zobaczyć analizę zużycia wraz z wagami przypisanymi do danego okresu w roku np. większa sprzedaż wycieraczek do domu w okresie jesiennym. Wtedy też system podpowie ci aby zwiększyć stany magazynowe by podołać zapotrzebowaniu."
  },
          {
    id: "4",
    nameHeader: "Cena",
    avatar: MonetizationOnIcon,
    route: "prices",
    component: PriceCard,
    subTitle: "Wykres pokazujący ceny z ostatnich 6 miesięcy. Wchodząc w szczegóły tej karty możesz zobaczyć historię cen danego towaru/artykułu. Wykresy z wybranego przez siebie okresu i wiele więcej."
  },
            {
    id: "5",
    nameHeader: "Szczegóły",
    avatar: DescriptionIcon,
    route: "details",
    component: DetailsCard,
    subTitle: "Znajdziesz tu wszystkie wymiary, wagi, zdjęcia, karty surowca i uwagi. Dla ułatwienia podgląd do najważniejszych atrybutów jest poniżej."
  },
];

export default ThingsDetailsItems;