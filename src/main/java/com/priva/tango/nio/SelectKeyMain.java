package com.priva.tango.nio;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class SelectKeyMain {

	/**
	 * @throws Exception 
	 * 
	 */
	public static void main(String[] args) throws Exception {
		Selector selector = Selector.open();
		//...
		Set<SelectionKey> selectionKeys = selector.selectedKeys();//
		Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
		SelectionKey sk = keyIterator.next();
		
		sk.interestOps(SelectionKey.OP_READ);
		sk.isAcceptable();
		sk.isConnectable();
		sk.isReadable();
		sk.isValid();
	}
}
