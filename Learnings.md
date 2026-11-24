Password is set via Run-EditConfigurations-Environment Variables (this works only for test runner)
Set the env variable via PowerShell ( Both commands must run in the same window, since the variable disappears when the window closes.)
$env:LETSSHOP_PASSWORD="YourPassword"
mvn clean test


A static field has one copy shared by every instance, so all scenarios read and write the same data. 
In parallel runs, scenarios overwrite each other's values, causing random failures or false passes. 
Even when running one at a time, static data carries over from the previous scenario, making tests depend on run order.
Non-static fields, created fresh per scenario by PicoContainer, give each scenario its own isolated data."