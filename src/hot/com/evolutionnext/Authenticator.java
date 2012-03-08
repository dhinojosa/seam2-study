package com.evolutionnext;

import javax.ejb.Local;

@Local
public interface Authenticator {

    boolean authenticate();

}
