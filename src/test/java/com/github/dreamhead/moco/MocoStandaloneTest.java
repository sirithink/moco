package com.github.dreamhead.moco;

import com.github.dreamhead.moco.helper.MocoTestHelper;
import com.github.dreamhead.moco.runner.JsonRunner;
import com.google.common.io.Resources;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MocoStandaloneTest {
    private static JsonRunner runner;
    private final MocoTestHelper helper = new MocoTestHelper();

    @Before
    public void setup() throws IOException {
        runner = new JsonRunner();
        runner.run(Resources.getResource("foo.json").openStream());
    }

    @After
    public void teardown() {
        runner.stop();
    }

    @Test
    public void should_return_expected_response() throws IOException {
        assertThat(helper.get("http://localhost:8080"), is("foo"));
    }

    @Test
    public void should_return_expected_response_with_text_api() throws IOException {
        assertThat(helper.get("http://localhost:8080/foo"), is("bar"));
    }

    @Test
    public void should_return_expected_response_with_file_api() throws IOException {
        assertThat(helper.get("http://localhost:8080/file"), is("foo.response"));
    }
}