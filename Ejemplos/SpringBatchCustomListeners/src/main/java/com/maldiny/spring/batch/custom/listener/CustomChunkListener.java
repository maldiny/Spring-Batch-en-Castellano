package com.maldiny.spring.batch.custom.listener;

import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;

public class CustomChunkListener implements ChunkListener {

	@Override
	public void afterChunk(ChunkContext arg0) {
		
		// System.out.println(">> CustomChunkListener >> After chunk execution: " + arg0);

	}

	@Override
	public void afterChunkError(ChunkContext arg0) {

		// System.out.println(">> CustomChunkListener >> After chunk ERROR execution: " + arg0);
		
	}

	@Override
	public void beforeChunk(ChunkContext arg0) {

		// System.out.println(">> CustomChunkListener >> Before chunk execution: " + arg0);
		
	}

}
