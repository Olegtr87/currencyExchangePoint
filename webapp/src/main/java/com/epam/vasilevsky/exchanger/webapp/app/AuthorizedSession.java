package com.epam.vasilevsky.exchanger.webapp.app;

import javax.inject.Inject;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;

import com.epam.vasilevsky.exchanger.datamodel.UserCredentials;
import com.epam.vasilevsky.exchanger.service.UserCredentialsService;
import com.epam.vasilevsky.exchanger.service.UserService;

public class AuthorizedSession extends AuthenticatedWebSession {

	@Inject
	private UserCredentialsService userCredentialsService;

	private UserCredentials loggedUser;

	private Roles roles;

	public AuthorizedSession(Request request) {
		super(request);
		Injector.get().inject(this);
	}

	public static AuthorizedSession get() {
		return (AuthorizedSession) Session.get();
	}

	@Override
	protected boolean authenticate(String login, String password) {
		loggedUser = userCredentialsService.findByLoginAndPassword(login, password);
		return loggedUser != null;
	}

	@Override
	public Roles getRoles() {
		if (isSignedIn() && (roles == null)) {
			roles = new Roles();
			roles.addAll(userCredentialsService.resolveRoles(loggedUser.getId()));
		}
		return roles;
	}

	public UserCredentials getLoggedUser() {
		return loggedUser;
	}

	@Override
	public void signOut() {
		super.signOut();
		loggedUser = null;
		roles = null;
	}

}
