
import Admin from "./pages/Admin";
import Basket from "./pages/Basket";
import ClothesPage from "./pages/ClothesPage";
import Main from "./pages/Main";
import Authorization from './pages/Authorization';
import { ADMIN_ROUTE, BASKET_ROUTE, CLOTHESPAGE_ROUTE, LOGIN_ROUTE, MAIN_ROUTE, REGISTRATION_ROUTE } from "./utils/consts";

export const authorizationRoutes =
[
  {
    path: ADMIN_ROUTE,
    Element: Admin
  },

  {
    path: BASKET_ROUTE,
    Element: Basket
  }
]

export const publicRoutes =
[
  {
    path: MAIN_ROUTE,
    Element: Main
  },

  {
    path: LOGIN_ROUTE,
    Element: Authorization
  },

  {
    path: REGISTRATION_ROUTE,
    Element: Authorization
  },

  {
    path: CLOTHESPAGE_ROUTE + '/:id',
    Element: ClothesPage
  }
]