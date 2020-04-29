@smoke
Feature: 搭建接口测试项目
  # 此注解是为了找到用例脚本文件路径
  @CASE_INFO(DEMO_case001)
  Scenario: 第一个测试流程
    Given 初始化数据<userNmme>
    When 测试接口调用<Login>