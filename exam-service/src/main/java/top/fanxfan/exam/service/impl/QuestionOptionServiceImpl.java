package top.fanxfan.exam.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.fanxfan.exam.repository.QuestionOptionRepository;
import top.fanxfan.exam.service.QuestionOptionService;

/**
 * 试题选项Service实现
 *
 * @author fanxfan
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionOptionServiceImpl implements QuestionOptionService {

    private final QuestionOptionRepository questionOptionRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean remove(Long id) {
        questionOptionRepository.findById(id).ifPresent(questionOptionRepository::delete);
        return true;
    }
}
