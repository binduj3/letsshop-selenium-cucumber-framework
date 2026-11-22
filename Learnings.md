Password is set via Run-EditConfigurations-Environment Variables (this works only for test runner)
Set the env variable via PowerShell ( Both commands must run in the same window, since the variable disappears when the window closes.)
$env:LETSSHOP_PASSWORD="YourPassword"
mvn clean test
