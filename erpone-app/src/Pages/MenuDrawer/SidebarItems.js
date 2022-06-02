import InboxIcon from '@mui/icons-material/MoveToInbox';
import AddShoppingCartIcon from '@mui/icons-material/AddShoppingCart';
import DescriptionIcon from '@mui/icons-material/Description';
import FormatListNumberedIcon from '@mui/icons-material/FormatListNumbered';
import DomainIcon from '@mui/icons-material/Domain';
import WorkIcon from '@mui/icons-material/Work';
import MonetizationOnIcon from '@mui/icons-material/MonetizationOn';
import SettingsIcon from '@mui/icons-material/Settings';
import DashboardIcon from '@mui/icons-material/Dashboard';
import StackedLineChartIcon from '@mui/icons-material/StackedLineChart';

const SidebarItems = [
  
  {
    id: "1",
    nameHeader: "Production",
    icon: WorkIcon,
    subMenu: [{ id: "1", name: "Dashboard", route: "/production/dashboard", icon: DashboardIcon }, { id: "2", name: "Plan", route: "/production/orders", icon: InboxIcon}, { id: "3", name: "Budget", route: "/production/budget", icon: StackedLineChartIcon }]
  },
  {
    id: "2",
    nameHeader: "Planning",
    icon: FormatListNumberedIcon,
    subMenu: [{ id: "1", name: "Dashboard", route: "/planning/dashboard", icon: DashboardIcon }, { id: "2", name: "subMenu2", route: "/planning/orders", icon: InboxIcon }]
  },
  {
    id: "3",
    nameHeader: "Purchase",
    icon: AddShoppingCartIcon,
    subMenu: [{ id: "1", name: "Dashboard", route: "/purchase/dashboard", icon: DashboardIcon }, { id: "2", name: "Documents", route: "/purchase/documents", icon: DescriptionIcon }, { id: "3", name: "Offers", route: "/purchase/offers", icon: InboxIcon }, { id: "4", name: "Budget", route: "/purchase/budget", icon: StackedLineChartIcon }]
  },
  {
    id: "4",
    nameHeader: "Sales",
    icon: MonetizationOnIcon,
    subMenu: [{ id: "1", name: "Dashboard", route: "/sales/dashboard", icon: DashboardIcon }, { id: "2", name: "Documents", route: "/sales/documents", icon: DescriptionIcon}, { id: "3", name: "Offers", route: "/sales/offers",  icon: InboxIcon }, { id: "4", name: "Budget", route: "/sales/budget", icon: StackedLineChartIcon }]
  },
  {
    id: "5",
    nameHeader: "Warehouse",
    icon: DomainIcon,
    subMenu: [{ id: "1", name: "Dashboard", route: "/warehouse/dashboard", icon: DashboardIcon }, { id: "2", name: "Documents", route: "/warehouse/documents", icon: DescriptionIcon}, { id: "3", name: "Things", route: "/warehouse/things", icon: InboxIcon }]
  },
  {
    id: "6",
    nameHeader: "Settings",
    icon: SettingsIcon,
    route: "/settings",
  }
];

export default SidebarItems;