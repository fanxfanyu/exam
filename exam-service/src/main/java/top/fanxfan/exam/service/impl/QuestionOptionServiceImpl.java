package top.fanxfan.exam.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.fanxfan.exam.service.QuestionOptionService;
import top.fanxfan.exam.repository.QuestionOptionRepository;

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
}
