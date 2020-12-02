import InboxIcon from '@material-ui/icons/MoveToInbox';
import AddShoppingCartIcon from '@material-ui/icons/AddShoppingCart';
import DescriptionIcon from '@material-ui/icons/Description';
import FormatListNumberedIcon from '@material-ui/icons/FormatListNumbered';
import DomainIcon from '@material-ui/icons/Domain';
import WorkIcon from '@material-ui/icons/Work';
import MonetizationOnIcon from '@material-ui/icons/MonetizationOn';

const SidebarItems = [
  
  {
    id: "1",
    nameHeader: "Production",
    icon: WorkIcon,
    subMenu: [{ id: "1", name: "Dashboard", route: "/production/dashboard", icon: InboxIcon }, { id: "2", name: "Plan", route: "/production/orders", icon: InboxIcon}]
  },
  {
    id: "2",
    nameHeader: "Planning",
    icon: FormatListNumberedIcon,
    subMenu: [{ id: "1", name: "Dashboard", route: "/planning/dashboard", icon: InboxIcon }, { id: "2", name: "subMenu2", route: "/planning/orders", icon: InboxIcon }]
  },
  {
    id: "3",
    nameHeader: "Purchase",
    icon: AddShoppingCartIcon,
    subMenu: [{ id: "1", name: "Dashboard", route: "/purchase/dashboard", icon: InboxIcon }, { id: "2", name: "Orders", route: "/purchase/orders", icon: InboxIcon }, { id: "3", name: "Offers", route: "/purchase/offers", icon: InboxIcon }]
  },
  {
    id: "4",
    nameHeader: "Sales",
    icon: MonetizationOnIcon,
    subMenu: [{ id: "1", name: "Dashboard", route: "/sales/dashboard", icon: InboxIcon }, { id: "2", name: "Orders", route: "/sales/orders", icon: InboxIcon}, { id: "3", name: "Offers", route: "/sales/offers",  icon: InboxIcon }]
  },
  {
    id: "5",
    nameHeader: "Warehouse",
    icon: DomainIcon,
    subMenu: [{ id: "1", name: "Warehouse", route: "/warehouse/dashboard", icon: InboxIcon }, { id: "2", name: "Things", route: "/warehouse/things", icon: InboxIcon }, { id: "3", name: "Documents", route: "/warehouse/documents", icon: DescriptionIcon}]
  }
];

export default SidebarItems;