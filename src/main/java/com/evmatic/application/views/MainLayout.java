package com.evmatic.application.views;

import com.evmatic.application.data.entity.User;
import com.evmatic.application.security.AuthenticatedUser;
import com.evmatic.application.views.analysisview.AnalysisViewView;
import com.evmatic.application.views.applicationsettings.ApplicationSettingsView;
import com.evmatic.application.views.badgeprinting.BadgePrintingView;
import com.evmatic.application.views.categoriesmanagement.CategoriesManagementView;
import com.evmatic.application.views.countrieslist.CountriesListView;
import com.evmatic.application.views.dashboard.DashboardView;
import com.evmatic.application.views.emailformatter.EmailFormatterView;
import com.evmatic.application.views.eventslist.EventsListView;
import com.evmatic.application.views.exportdata.ExportDataView;
import com.evmatic.application.views.giveawaymanagement.GiveawayManagementView;
import com.evmatic.application.views.onsiteregistration.OnsiteRegistrationView;
import com.evmatic.application.views.referencelist.ReferenceListView;
import com.evmatic.application.views.sessionsmanagement.SessionsManagementView;
import com.evmatic.application.views.templatedesign.TemplateDesignView;
import com.evmatic.application.views.txtupload.TxtUploadView;
import com.evmatic.application.views.uploaddata.UploadDataView;
import com.evmatic.application.views.usersmanagement.UsersManagementView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import com.vaadin.flow.theme.lumo.LumoUtility;
import java.io.ByteArrayInputStream;
import java.util.Optional;
import org.vaadin.lineawesome.LineAwesomeIcon;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H2 viewTitle;

    private AuthenticatedUser authenticatedUser;
    private AccessAnnotationChecker accessChecker;

    public MainLayout(AuthenticatedUser authenticatedUser, AccessAnnotationChecker accessChecker) {
        this.authenticatedUser = authenticatedUser;
        this.accessChecker = accessChecker;

        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("EvMatic App");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();

        if (accessChecker.hasAccess(DashboardView.class)) {
            nav.addItem(new SideNavItem("Dashboard", DashboardView.class, LineAwesomeIcon.CHART_LINE_SOLID.create()));

        }
        if (accessChecker.hasAccess(OnsiteRegistrationView.class)) {
            nav.addItem(new SideNavItem("Onsite Registration", OnsiteRegistrationView.class,
                    LineAwesomeIcon.ADDRESS_BOOK_SOLID.create()));

        }
        if (accessChecker.hasAccess(BadgePrintingView.class)) {
            nav.addItem(
                    new SideNavItem("Badge Printing", BadgePrintingView.class, LineAwesomeIcon.QRCODE_SOLID.create()));

        }
        if (accessChecker.hasAccess(ReferenceListView.class)) {
            nav.addItem(new SideNavItem("Reference List", ReferenceListView.class,
                    LineAwesomeIcon.LIST_ALT_SOLID.create()));

        }
        if (accessChecker.hasAccess(GiveawayManagementView.class)) {
            nav.addItem(new SideNavItem("Giveaway Management", GiveawayManagementView.class,
                    LineAwesomeIcon.GIFTS_SOLID.create()));

        }
        if (accessChecker.hasAccess(CategoriesManagementView.class)) {
            nav.addItem(new SideNavItem("Categories Management", CategoriesManagementView.class,
                    LineAwesomeIcon.OBJECT_GROUP_SOLID.create()));

        }
        if (accessChecker.hasAccess(SessionsManagementView.class)) {
            nav.addItem(new SideNavItem("Sessions Management", SessionsManagementView.class,
                    LineAwesomeIcon.LAYER_GROUP_SOLID.create()));

        }
        if (accessChecker.hasAccess(UploadDataView.class)) {
            nav.addItem(new SideNavItem("Upload Data", UploadDataView.class,
                    LineAwesomeIcon.CLOUD_UPLOAD_ALT_SOLID.create()));

        }
        if (accessChecker.hasAccess(ExportDataView.class)) {
            nav.addItem(new SideNavItem("Export Data", ExportDataView.class,
                    LineAwesomeIcon.CLOUD_DOWNLOAD_ALT_SOLID.create()));

        }
        if (accessChecker.hasAccess(CountriesListView.class)) {
            nav.addItem(
                    new SideNavItem("Countries List", CountriesListView.class, LineAwesomeIcon.GLOBE_SOLID.create()));

        }
        if (accessChecker.hasAccess(EventsListView.class)) {
            nav.addItem(new SideNavItem("Events List", EventsListView.class,
                    LineAwesomeIcon.CLIPBOARD_LIST_SOLID.create()));

        }
        if (accessChecker.hasAccess(AnalysisViewView.class)) {
            nav.addItem(new SideNavItem("Analysis View", AnalysisViewView.class, LineAwesomeIcon.CHART_BAR.create()));

        }
        if (accessChecker.hasAccess(EmailFormatterView.class)) {
            nav.addItem(new SideNavItem("Email Formatter", EmailFormatterView.class,
                    LineAwesomeIcon.MAIL_BULK_SOLID.create()));

        }
        if (accessChecker.hasAccess(TemplateDesignView.class)) {
            nav.addItem(
                    new SideNavItem("Template Design", TemplateDesignView.class, LineAwesomeIcon.ARTSTATION.create()));

        }
        if (accessChecker.hasAccess(TxtUploadView.class)) {
            nav.addItem(new SideNavItem("Txt Upload", TxtUploadView.class, LineAwesomeIcon.FILE_UPLOAD_SOLID.create()));

        }
        if (accessChecker.hasAccess(UsersManagementView.class)) {
            nav.addItem(new SideNavItem("Users Management", UsersManagementView.class,
                    LineAwesomeIcon.USER_PLUS_SOLID.create()));

        }
        if (accessChecker.hasAccess(ApplicationSettingsView.class)) {
            nav.addItem(new SideNavItem("Application Settings", ApplicationSettingsView.class,
                    LineAwesomeIcon.TOOLS_SOLID.create()));

        }

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        Optional<User> maybeUser = authenticatedUser.get();
        if (maybeUser.isPresent()) {
            User user = maybeUser.get();

            Avatar avatar = new Avatar(user.getName());
            StreamResource resource = new StreamResource("profile-pic",
                    () -> new ByteArrayInputStream(user.getProfilePicture()));
            avatar.setImageResource(resource);
            avatar.setThemeName("xsmall");
            avatar.getElement().setAttribute("tabindex", "-1");

            MenuBar userMenu = new MenuBar();
            userMenu.setThemeName("tertiary-inline contrast");

            MenuItem userName = userMenu.addItem("");
            Div div = new Div();
            div.add(avatar);
            div.add(user.getName());
            div.add(new Icon("lumo", "dropdown"));
            div.getElement().getStyle().set("display", "flex");
            div.getElement().getStyle().set("align-items", "center");
            div.getElement().getStyle().set("gap", "var(--lumo-space-s)");
            userName.add(div);
            userName.getSubMenu().addItem("Sign out", e -> {
                authenticatedUser.logout();
            });

            layout.add(userMenu);
        } else {
            Anchor loginLink = new Anchor("login", "Sign in");
            layout.add(loginLink);
        }

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
