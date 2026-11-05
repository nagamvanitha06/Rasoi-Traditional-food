enum AppLanguage {
  english('English', 'en', 'English'),
  hindi('Hindi', 'hi', 'हिंदी'),
  telugu('Telugu', 'te', 'తెలుగు');

  final String displayName;
  final String code;
  final String nativeName;

  const AppLanguage(this.displayName, this.code, this.nativeName);
}

class LocalizedText {
  final String en;
  final String hi;
  final String te;

  const LocalizedText({required this.en, required this.hi, required this.te});

  String get(AppLanguage lang) {
    switch (lang) {
      case AppLanguage.english:
        return en;
      case AppLanguage.hindi:
        return hi;
      case AppLanguage.telugu:
        return te;
    }
  }
}
