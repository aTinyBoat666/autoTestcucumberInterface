@smoke
Feature: 搭建接口测试项目
  # 此注解是为了找到用例脚本文件路径
    @CASE_INFO(DEMO_case001)
  Scenario Outline: 第一个测试流程
    Given 初始化数据<userNmme>
    When 测试接口调用<Login>,密码<password>
    @P1
    Examples:
      | userNmme | password     |
      | zhangsan       | 1234|
      | 李四       | 567 |
      | 王五       | 888 |

