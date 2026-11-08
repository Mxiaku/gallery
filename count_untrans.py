import re
src=open('Android/src/app/src/main/res/values/strings.xml',encoding='utf-8').read()
trans=open('Android/src/app/src/main/res/values-es/strings.xml',encoding='utf-8').read()
src_entries = re.findall(r'<string name="([^"]+)"[^>]*>(.*?)</string>', src, re.DOTALL)
trans_entries = re.findall(r'<string name="([^"]+)"[^>]*>(.*?)</string>', trans, re.DOTALL)
trans_dict = {n:v for n,v in trans_entries}
untranslated = 0
for name, val in src_entries:
    if name in trans_dict and trans_dict[name] == val and len(val) > 3 and not val.startswith('@'):
        untranslated += 1
print('untranslated user-visible strings:', untranslated)
