package com.github.huorehu.test_store;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConnectionManagerTest {

    private ConnectionManager connectionManager = null;

    @Before
    public void prepareEnvironment() {
	connectionManager = mock(ConnectionManager.class);
    }

    @After
    public void cleanEnviroment() {
	connectionManager = null;
    }

    @Test
    public void add_row_count_added_rows() {
	// given
	when(connectionManager.addRow()).thenReturn(1);

	// when
	int result = connectionManager.addRow();
	// then
	assertEquals(1, result);
	verify(connectionManager).addRow();
    }

    @Test
    public void close_scanner() {
	// given
	when(connectionManager.closeScanner()).thenReturn(true);

	// when
	boolean result = connectionManager.closeScanner();

	// then
	assertTrue(result);
	verify(connectionManager).closeScanner();
    }

    @Test
    public void update_row_count_added_rows() {
	// given
	when(connectionManager.updateData(1, "testName")).thenReturn(1);

	// when
	int result = connectionManager.updateData(1, "testName");

	// then
	assertEquals(1, result);
	verify(connectionManager).updateData(1, "testName");
    }

    @Test
    public void show_table_count_added_rows() {
	// given
	when(connectionManager.showTable()).thenReturn(17);

	// when
	int result = connectionManager.showTable();

	// then
	assertEquals(17, result);
	verify(connectionManager).showTable();
    }

    @Test
    public void delete_data_delete_rows() {
	// given
	when(connectionManager.deleteData(4)).thenReturn(true);

	// when
	boolean result = connectionManager.deleteData(4);

	// then
	assertTrue(result);
	verify(connectionManager).deleteData(4);

    }

}
