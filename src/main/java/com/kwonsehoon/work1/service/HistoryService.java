package com.kwonsehoon.work1.service;

import com.kwonsehoon.work1.dto.history.HistoryDto;
import com.kwonsehoon.work1.dto.history.HistoryResponseDto;
import com.kwonsehoon.work1.repository.HistoryRepository;
import lombok.NoArgsConstructor;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class HistoryService {
    HistoryRepository historyRepository = HistoryRepository.getInstance();
    private static final HistoryService instance = new HistoryService();

    public static HistoryService getInstance() {
        return instance;
    }

    public void createHistory(double x, double y) throws SQLException, ClassNotFoundException {
        historyRepository.create(x, y);
    }

    public List<HistoryResponseDto> findAllHistory() throws SQLException, ClassNotFoundException {
        return historyRepository.findAll()
                .stream()
                .map(x -> new HistoryResponseDto(x.getId(), x.getLat(), x.getLnt(), x.getDate()))
                .collect(Collectors.toList());
    }

    public void deleteHistory(int historyId) throws SQLException, ClassNotFoundException {
        HistoryDto history = historyRepository.findOne(historyId);

        if (history != null) historyRepository.delete(historyId);
        else System.out.println("존재하지 않거나 이미 삭제된 History 입니다.");
    }
}
