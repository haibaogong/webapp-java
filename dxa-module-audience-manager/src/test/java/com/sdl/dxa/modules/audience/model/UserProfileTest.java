package com.sdl.dxa.modules.audience.model;

import com.tridion.marketingsolution.profile.Contact;
import com.tridion.marketingsolution.utilities.TcmUri;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserProfileTest {

    @Mock
    private UserProfile profile;

    @Mock
    private PasswordEncoder encoder;


    @Before
    public void init() {
        when(profile.isAccountNonExpired()).thenCallRealMethod();
        when(profile.isAccountNonLocked()).thenCallRealMethod();
        when(profile.isCredentialsNonExpired()).thenCallRealMethod();
        when(profile.isEnabled()).thenCallRealMethod();
        when(profile.getAuthorities()).thenCallRealMethod();
        when(profile.verifyPassword(anyString())).thenCallRealMethod();

        when(profile.getPasswordEncoder()).thenReturn(encoder);
    }

    @Test
    public void shouldDefaultAllPositiveDefaultAnswersForUnsupportedMethods() {
        assertTrue(profile.isAccountNonExpired());
        assertTrue(profile.isAccountNonLocked());
        assertTrue(profile.isCredentialsNonExpired());
        assertTrue(profile.isEnabled());

        assertEquals("ROLE_USER", profile.getAuthorities().iterator().next().getAuthority());
    }

    @Test
    public void shouldVerifyEncryptedPassword() {
        //given
        when(profile.getPassword()).thenReturn("encrypted:password");
        when(encoder.matches(eq("password"), eq("password"))).thenReturn(true);

        //when
        boolean valid = profile.verifyPassword("password");
        boolean notValid = profile.verifyPassword("wrong password");

        //then
        assertTrue(valid);
        assertFalse(notValid);
        verify(encoder).matches(eq("password"), eq("password"));
        verify(encoder).matches(eq("wrong password"), eq("password"));
    }

    @Test
    public void shouldVerifyRawPassword() {
        when(profile.getPassword()).thenReturn("rawPassword");
        when(encoder.matches(eq("rawPassword"), eq("rawPassword"))).thenReturn(true);

        //when
        boolean valid = profile.verifyPassword("rawPassword");
        boolean notValid = profile.verifyPassword("wrong rawPassword");

        //then
        assertTrue(valid);
        assertFalse(notValid);
        verifyZeroInteractions(encoder);
    }

    @Test
    public void shouldTreatNullAsFlaseMatchForPassword() {
        //when
        boolean notValid = profile.verifyPassword(null);

        //then
        assertFalse(notValid);
    }

    @Test
    public void shouldGetIdAndUsernameAndPasswordFromContact() throws IOException, SQLException {
        //given
        Contact contact = mock(Contact.class);
        when(contact.getExtendedDetail("uKey")).thenReturn("user");
        when(contact.getExtendedDetail("pKey")).thenReturn("pass");
        TcmUri tcmUri = mock(TcmUri.class);
        when(tcmUri.toString()).thenReturn("id");
        when(contact.getId()).thenReturn(tcmUri);

        //when
        UserProfile profile = new UserProfile(contact, "user", "uKey", "pKey", new ContactIdentifiers("uKey", "DXA"));

        //then
        assertEquals("user", profile.getUsername());
        assertEquals("pass", profile.getPassword());
        assertEquals("id", profile.getId());
    }

    @Test
    public void shouldReturnNonNullPasswordEncoder() {
        //when
        PasswordEncoder encoder = new UserProfile(null, null, null, null, null).getPasswordEncoder();

        //then
        assertNotNull(encoder);
    }

    @Test
    public void shouldEraseCredentials() {
        //given
        Contact contact = mock(Contact.class);

        //when
        UserProfile userProfile = new UserProfile(contact, null, null, "pKey", null);
        userProfile.eraseCredentials();

        //then
        verify(contact).setExtendedDetail(eq("pKey"), isNull(String.class));
        //noinspection ConstantConditions
        assertTrue(userProfile instanceof CredentialsContainer);
    }
}